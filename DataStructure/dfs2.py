from collections import defaultdict

class Graph:
    def __init__(self):
        self.graph = defaultdict(list)
    
    def addEdge(self, u, v):
        self.graph[u].append(v)
    
    def DFSHelper(self, v, visited):
        visited[v] = True
        print(v, end = ' ')

        for i in self.graph[v]:
            if visited[i] == False:
                self.DFSHelper(i, visited)
    
    def DFS(self, v):
        visited = [False] * (max(self.graph)+1)
        self.DFSHelper(v, visited)

