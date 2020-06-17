def bfs(graph, start):
    visited = list()
    queue = list()

    queue.append(start)

    while queue:
        #dequeue
        node = queue.pop(0)
        if node not in visited:
            visited.append(node)
            queue.extend(graph[node])

    return visited