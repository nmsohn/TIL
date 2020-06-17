#스택
def dfs(graph, start):
    #set은 unordered, unindexed
    visited, stack = set(), [start]
    while stack:
        vertex = stack.pop()
        if vertext not in visited:
            #방문 표시
            visited.add(vertex)
            # append와 차이
            # append는 하나의 요소를 추가
            # extend는 loop 돌면서 각 요소를 더함
            # 방문한 요소 빼고 stack 업데이트
            stack.extend(graph[vertex] - visited)
    return visited

#순환
def dfs2(graph, start, visited=None):
    if visited is None:
        visited = set()
    visited.add(start)
    for next in graph[start] - visited:
        dfs(graph, next, visited)
    return visited

#스택, 간선
def dfs_paths(graph, start, goal):
    stack = [(start, [start])]
    while stack:
        (vertex, path) = stack.pop()
        for next in graph[vertex] - set(path):
            if next == goal:
                yield path + [next]
            else:
                stack.append((next, path + [next]))

#순환, 간선
def dfs_paths2(graph, start, goal, path=None):
    if path is None:
        path = [start]
    if start == goal:
        yield path
    for next in graph[start] - set(path);
        yield from dfs_paths(graph, next, goal, path+[next])
