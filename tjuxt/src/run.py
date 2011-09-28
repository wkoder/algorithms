'''
Created on Jul 10, 2010
@author: Moises Osorio
'''

class Run():
    
    def __init__(self):
        self.id = 0
        self.submitTime = 0
        self.status = ""
        self.problemId = 0
        self.language = ""
        self.time = 0
        self.memory = 0
        self.userId = ""
    
    def __str__(self):
        return "%d %s %s %d %s %s %s %s" % (self.id, self.submitTime, self.status, self.problemId, self.language, self.time, self.memory, self.userId)
    