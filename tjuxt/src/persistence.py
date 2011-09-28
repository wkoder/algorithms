'''
Created on Jul 10, 2010
@author: Moises Osorio
'''
from problem import Problem
from run import Run
from sqlite3 import dbapi2 as db
from user import User
import os

DATABASE_NAME = "tjuxt.db"
PROBLEM_COLUMNS = "id, title, accepted, submitted, ratio, source"
RUN_COLUMS = "id, submitTime, problemId, status, time, language, memory, userId"
USER_COLUMNS = "id, name, country, solved, submitted, ratio"

class Persistence():

    def __init__(self):
        create = not os.path.exists(DATABASE_NAME)
        self.connection = db.connect(DATABASE_NAME)
        self.cursor = self.connection.cursor()
        if create:
            self.createDatabase()
        
    def createDatabase(self):
        self.cursor.execute("CREATE TABLE users (id VARCHAR PRIMARY KEY, name VARCHAR, country VARCHAR, solved INTEGER, submitted INTEGER, ratio FLOAT)")
        self.cursor.execute("CREATE TABLE problems (id INTEGER PRIMARY KEY, title VARCHAR, accepted INTEGER, submitted INTEGER, ratio FLOAT, status INTEGER, source VARCHAR)")
        self.cursor.execute("CREATE TABLE runs (id VARCHAR PRIMARY KEY, submitTime VARCHAR, problemId INTEGER, status VARCHAR, time INTEGER, language VARCHAR, memory INTEGER, userId VARCHAR)")
        self.cursor.execute("CREATE TABLE vars (name VARCHAR PRIMARY KEY, value VARCHAR)")
        
    def commit(self):
        self.connection.commit()
    
    def rollback(self):
        self.connection.rollback()
        
    def close(self):
        self.connection.close()
        
    def _existsId(self, table, id, columnId = "id"):
        query = "SELECT %s FROM %s WHERE %s = ?" % (columnId, table, columnId)
        self.cursor.execute(query, [id])
        return len(self.cursor.fetchall()) > 0
        
    def updateVar(self, name, value):
        if self._existsId("vars", name, "name"):
            query = "UPDATE vars SET value = '%s' WHERE name LIKE ?"
        else:
            query = "INSERT INTO vars (name, value) VALUES (?, '%s')"
        query = query % (value)
        self.cursor.execute(query, [name])
        
    def getVar(self, name):
        self.cursor.execute("SELECT value FROM vars WHERE name LIKE ?", [name])
        value = self.cursor.fetchone()
        if value == None:
            return None
        return value[0]
        
    def updateProblem(self, problem):
        exists = self._existsId("problems", problem.id)
        data = [problem.title, problem.accepted, problem.submitted, problem.ratio, problem.source]
        if not exists:
            query = "INSERT INTO problems (%s) VALUES (?, ?, ?, ?, ?, ?)" % PROBLEM_COLUMNS
            data.insert(0, problem.id)
        else:
            data.append(problem.id)
            query = "UPDATE problems SET title=?, accepted=?, submitted=?, ratio=?, source=? WHERE id = ?"
        
        self.cursor.execute(query, data)
        return not exists
    
    def updateRun(self, run):
        exists = self._existsId("runs", run.id)
        data = [run.submitTime, run.problemId, run.status, run.time, run.language, run.memory, run.userId]
        if not exists:
            query = "INSERT INTO runs (%s) VALUES (?, ?, ?, ?, ?, ?, ?, ?)" % RUN_COLUMS
            data.insert(0, run.id)
        else:
            query = "UPDATE runs SET submitTime=?, problemId=?, status=?, time=?, language=?, memory=?, userId=? WHERE id = ?"
            data.append(run.id)
        
        self.cursor.execute(query, data)
        return not exists
    
    def updateUser(self, user):
        exists = self._existsId("runs", user.id)
        data = [user.name, user.country, user.solved, user.submitted, user.ratio]
        if not exists:
            query = "INSERT INTO users (%s) VALUES(?, ?, ?, ?, ?, ?)" % USER_COLUMNS
            data.insert(0, user.id)
        else:
            query = "UPDATE users SET name=?, country=?, solved=?, submitted=?, ratio=? WHERE id = ?"
            data.append(user.id)
            
        self.cursor.execute(query, data)
        return not exists
    
    def countUserRuns(self, userId):
        self.cursor.execute("SELECT COUNT(*) FROM runs WHERE userId LIKE ?", [userId])
        data = self.cursor.fetchone()
        return data[0]
    
    def getProblem(self, problemId):
        query = "SELECT %s FROM problems WHERE id = ?" % PROBLEM_COLUMNS
        self.cursor.execute(query, [problemId])
        data = self.cursor.fetchone()
        if data == None:
            return None
        return self._parseProblem(data)
        
    def existsSource(self, contest):
        self.cursor.execute("SELECT DISTINCT source FROM problems WHERE source LIKE ?", [contest])
        return len(self.cursor.fetchall()) > 0
    
    def getRanking(self, country = None):
        if country == None:
            query = "SELECT %s FROM users ORDER BY solved DESC, ratio DESC, submitted" % USER_COLUMNS
        else:
            query = "SELECT %s FROM users WHERE country LIKE '%s' ORDER BY solved DESC, ratio DESC, submitted" % (USER_COLUMNS, country)
        self.cursor.execute(query)
        ranking = list()
        data = self.cursor.fetchone()
        while data != None:
            ranking.append(self._parseUser(data))
            data = self.cursor.fetchone()
        return ranking
    
    def getUser(self, userId):
        query = "SELECT %s FROM users WHERE id LIKE ?" % USER_COLUMNS
        self.cursor.execute(query, [userId])
        data = self.cursor.fetchone()
        if data == None:
            return None
        return self._parseUser(data)
    
    def getContest(self, contest):
        query = "SELECT %s FROM problems WHERE source LIKE ? ORDER BY id" % PROBLEM_COLUMNS
        self.cursor.execute(query, [contest])
        problems = list()
        data = self.cursor.fetchone()
        while data != None:
            problems.append(self._parseProblem(data))
            data = self.cursor.fetchone()
            
        return problems
    
    def _parseProblem(self, data):
        problem = Problem()
        problem.id = data[0]
        problem.title = data[1]
        problem.accepted = data[2]
        problem.submitted = data[3]
        problem.ratio = data[4]
        problem.source = data[5]
        
        return problem
    
    def _parseRun(self, data):
        run = Run()
        run.id = data[0]
        run.submitTime = data[1]
        run.problemId= data[2]
        run.status = data[3]
        run.time = data[4]
        run.language = data[5]
        run.memory = data[6]
        run.userId = data[7]
        
        return run
    
    def _parseUser(self, data):
        user = User()
        user.id = data[0]
        user.name = data[1]
        user.country = data[2]
        user.solved = data[3]
        user.submitted = data[4]
        user.ratio = data[5]
        
        return user
    