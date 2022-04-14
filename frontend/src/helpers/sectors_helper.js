export const parseRoots = (data) => {
    if (!data) {
        return null
    }
    return data.sort((a, b) => {
        const nameA = a.name.toUpperCase();
        const nameB = b.name.toUpperCase();
        if (nameA < nameB) {
            return -1;
        }
        if (nameA > nameB) {
            return 1;
        }
        return 0;
    }).map(s => ({label: s.name, value: s.id, children: parseRoots(s.children)}))
}

export const parseRootsToList = (data) => {
    if (!data) {
        return []
    }
    let result = []
    data.forEach(r => addNodeToList(r, result))
    return result
}

const addNodeToList = (node, list) => {
    list.push({value: node.id, label: node.name})
    if (node.children) {
        node.children.forEach(c => addNodeToList(c, list))
    }
}

