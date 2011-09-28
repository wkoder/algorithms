'''
Created on Jul 9, 2010
@author: Moises Osorio
'''
class Problem():

    def __init__(self):
        self.id = 0
        self.title = ""
        self.accepted = 0
        self.submitted = 0
        self.ratio = 0
        self.source = ""
        
    def __str__(self):
        return "%d '%s' %d %d %.2f %s" % (self.id, self.title, self.accepted, self.submitted, self.ratio, self.source)
    