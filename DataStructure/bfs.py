def BFS(adj, s):
    queue = [s]
    visited = []
    while queue:
        vertex = queue.pop(0)

        if vertex not in visited:
            visited.append(vertex)
            for node in graph[vertex]:
                if node not in visited:
                    queue.append(node)
    return visited

#경로탐색

def bfs_path(graph, start, end, path=[]):
    path = path + [start]

    #목적지에 도달
    if start == end:
        paths.append(path)
    
    queue = [start]

    while queue:
        vertext = queue.pop(0)

        for node in graph[vertex]:
            if node not in path:
                bfs_path(graph, node, end, path)