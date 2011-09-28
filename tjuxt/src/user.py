'''
Created on Jul 10, 2010
@author: Moises Osorio
'''

class User():

    def __init__(self):
        self.id = ""
        self.name = ""
        self.country = ""
        self.solved = 0
        self.submitted = 0
        self.ratio = 0.0
    
    def __str__(self):
        return "%s %s %s %d %d %.2f" % (self.id, self.name, self.country, self.solved, self.submitted, self.ratio)
    
    def getACRatio(self):
        return self.solved / float(self.submitted) * 100