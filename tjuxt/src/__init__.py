'''
Created on Jul 9, 2010
@author: Moises Osorio
'''
from parsers import parseProblems, parseUserRuns, parseUsers, parseContests, \
    parseContest
from persistence import Persistence

VOLUME_SIZE = 100
RUNS_SIZE = 10
RANK_SIZE = 25
USERS_UPDATED = "usersUpdated"
USER_RUN_UPDATED = "runUpdated %s"
PROBLEM_VOLUME_UPDATED = "problemVolumeUpdated"

def updateProblems(force = False):
    volume = 0
    if not force:
        lastVolume = persistence.getVar(PROBLEM_VOLUME_UPDATED)
        if lastVolume != None:
            volume = int(lastVolume)
            
    ok = True
    while ok:
        volume += 1
        print "Updating problems volume %d" % volume
        problems = parseProblems(volume)
        for problem in problems:
            persistence.updateProblem(problem)
        
        if len(problems) == VOLUME_SIZE:
            persistence.updateVar(PROBLEM_VOLUME_UPDATED, volume)
        persistence.commit()
        ok = len(problems) > 0

def updateUserRuns(userId, force = False):
    print "Updating runs of user %s" % userId
    lastRunId = -1
    var = USER_RUN_UPDATED % userId
    if not force:
        lastRun = persistence.getVar(var)
        if lastRun != None:
            lastRunId = int(lastRun)
            
    user = persistence.getUser(userId)
    ok = True
    count = persistence.countUserRuns(userId)
    while ok:
        print "   Completed %.2f%s" % (count / float(user.submitted) * 100, "%")
        if lastRunId == -1:
            runs = parseUserRuns(userId)
        else:
            runs = parseUserRuns(userId, lastRunId-1)
        
        count += len(runs)
        for run in runs:
            lastRunId = run.id
            if not force and not persistence.updateRun(run):
                ok = False
                lastRunId = -1
                break
        
        persistence.updateVar(var, lastRunId)
        persistence.commit()
        
        ok = ok and len(runs) == RUNS_SIZE
    
def updateUsers(topRank, force = False):
    print "Updating top %d rank" % topRank
    updated = 0
    if not force:
        lastUpdated = persistence.getVar(USERS_UPDATED)
        if lastUpdated != None:
            updated = int(lastUpdated)
            
    while updated < topRank:
        print "   Completed %.2f%s" % (updated / float(topRank) * 100, "%")
        users = parseUsers(updated+1)
        for user in users:
            persistence.updateUser(user)
        
        updated += len(users)
        persistence.updateVar(USERS_UPDATED, updated)
        persistence.commit()
    
        
def updateContest(contest):
    print "Updating contest %s" % contest
    problemIds = parseContest(contest)
    for problemId in problemIds:
        problem = persistence.getProblem(problemId)
        if problem == None:
            print "Problem %d not found. Please update your problem data." % problemId
            return
        if problem.source != contest:
            problem.source = contest
            persistence.updateProblem(problem)
            persistence.commit()
    
def updateContests(force = False):
    print "Updating contests..."
    contests = parseContests()
    count = 0
    for contest in contests:
        count += 1
        print "   Completed %.2f%s" % (count / float(len(contests) * 100), "%")
        if force or not persistence.existsSource(contest):
            updateContest(contest)
            
def _showUserHeader(rank = False):
    if rank:
        print "Rank  Solved  Submitted  AC Ratio  Ratio  Name [ID]"
    else:
        print "Solved  Submitted  AC Ratio  Ratio  Name [ID]"
    
def _showUser(user, rank = 0):
    if rank > 0:
        print "%4d  %4d    %4d       %2.2f     %3.2f %s [%s]" % (rank, user.solved, user.submitted, user.getACRatio(), user.ratio, user.name, user.id)
    else:
        print "%4d    %4d       %2.2f     %3.2f %s [%s]" % (user.solved, user.submitted, user.getACRatio(), user.ratio, user.name, user.id)
    
def showUser(userId):
    user = persistence.getUser(userId)
    if user == None:
        print "User %s not found. Please update user data with command 'update users'." % userId
        return
    _showUserHeader()
    _showUser(user)
    
def showProblem(problemId):
    problem = persistence.getProblem(problemId)
    if problem == None:
        print "Problem %d not found. Please update problem data with command 'update problems'" % problemId
        return
    _showProblemHeader()
    _showProblem(problem)

def showRanking(country = None):
    _showUserHeader(True)
    ranking = persistence.getRanking(country)
    for i, user in enumerate(ranking):
        _showUser(user, i+1)

def _showProblemHeader():
    print "ID   Accepted  Submitted  Ratio  Source          Title"
    
def _showProblem(problem):
    print "%4d %5d     %5d      %2.2f  %15s %s" % (problem.id, problem.accepted, problem.submitted, problem.ratio, problem.source, problem.title)
    
def showContest(contest):
    problems = persistence.getContest(contest)
    _showProblemHeader()
    for problem in problems:
        _showProblem(problem)
    
def showUsage():
    print "Usage:"
    print "   update [force] (user $username | users [$top=1000] | problems | contests)"
    print "   show (user $username | contest $contest | ranking [$country])"

def execute(command):
    command = command.lower()
    tokens = command.split()
    if len(tokens) == 0:
        return
    if tokens[0] == "update":
        if len(tokens) == 1:
            showUsage()
            return
        
        force = False
        if tokens[1] == "force":
            force = True
            del tokens[1]
        
        if tokens[1] == "user":
            if len(tokens) == 2:
                showUsage()
                return
            updateUserRuns(tokens[2], force)
        elif tokens[1] == "users":
            top = 1000
            if len(tokens) > 2:
                if tokens[2].isdigit():
                    top = int(tokens[2])
                else:
                    showUsage()
                    return
            updateUsers(top, force)    
        elif tokens[1] == "problems":
            updateProblems(force)
        elif tokens[1] == "contests":
            updateContests(force)
    elif tokens[0] == "show":
        if len(tokens) == 1:
            showUsage()
            return
        
        if tokens[1] == "user":
            if len(tokens) == 2:
                showUsage()
                return
            showUser(tokens[2])
        elif tokens[1] == "contest":
            if len(tokens) == 2:
                showUsage()
                return
            contest = " ".join(tokens[2 : ])
            showContest(contest)
        elif tokens[1] == "ranking":
            if len(tokens) == 2:
                showRanking()
            else:
                showRanking(tokens[2])
        else:
            showUsage()
            return
    else:
        showUsage()

persistence = Persistence()

try:
    cmd = raw_input("> ")
    while True:
        execute(cmd)
        cmd = raw_input("\n> ")
except EOFError:
    None
finally:
    persistence.close()
