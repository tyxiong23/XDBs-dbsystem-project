from .Term import Term
from OutputSystem.basicClass.outputUnit import LookupOutput
from utils.exception import JoinError

class Join:
    def __init__(self, res_map: dict, term, union=None):
        self._join_map = {}
        self.res_map = res_map
        self._term = term
        self._union = union

    @staticmethod
    def get_values(value: tuple, block: tuple):
        return tuple(value[i] for i in block)
    
    @staticmethod
    def check_join(len_outside_joined, len_inside_joined):
        if len_outside_joined != len_inside_joined:
            raise JoinError(f"WRONG----JOIN ERROR {len_outside_joined} != {len_inside_joined}")
    
    def create_pair(self, term: Term):
        if term.aim_table is None:
            return None, None
        else :
            if term.table != term.aim_table:
                if term.operator == '=':
                    pairs = (term.table, term.col), (term.aim_table, term.aim_col)
                    sorted_pairs = zip(*sorted(pairs))
                    return tuple(sorted_pairs)
                else:
                    raise JoinError(f"WRONG----Join CREATE PAIR ERROR term.operator{term.operator}")
            else:
                return None, None

    def union_create(self):
        for key, elems in map(self.create_pair, self._term): 
            if elems is not None:
                if key not in self._join_map:
                    self._join_map[key] = ([elems[0]], [elems[1]])      
                else:
                    self._join_map[key][0].append(elems[0])
                    self._join_map[key][1].append(elems[1])    
        if not self._join_map:
            raise JoinError("WRONG----Join TABLES ERROR: not self._join_map")
        self._union = {key: key for key in self.res_map.keys()}

    def union_search(self, element):
        if element != self._union[element]:
            self._union[element] = self.union_search(self._union[element])
        return self._union[element]

    def union_merge(self, element_1, element_2):
        self._union[self.union_search(element_1)] = self.union_search(element_2)

    def get_output(self):
        result = None
        self.union_create()
        for pair in self._join_map:
            pair0 = pair[0]
            pair1 = pair[1]
            out: LookupOutput = self.res_map[pair[0]]
            inside: LookupOutput = self.res_map[pair[1]]
            outJoined = tuple(pair0 + "." + col for col in self._join_map[pair][0])
            inJoined = tuple(pair1 + "." + col for col in self._join_map[pair][1])
            temp = self.loop_join(out, inside, outJoined, inJoined)
            self.union_merge(pair0, pair1)
            new_key = self.union_search(pair0)
            self.res_map[new_key] = temp
            result = temp
        return result

    def create_join_value(self, outside: tuple, outside_joined: tuple):
        joinMap = {}
        index = 0 
        for elem in outside:
            value = self.get_values(elem, outside_joined)
            if value in joinMap:
                joinMap[value] = [index]
            else:
                joinMap[value].append(index)
            index += 1
        return joinMap

    def loop_join_data(self, outside: tuple, inside: tuple, outside_joined: tuple, inside_joined: tuple):
        self.check_join(len(outside_joined), len(inside_joined))      
        if len(outside) or len(inside):
            return None, None, None
        results = []
        inLeft = tuple(i for i in range(len(inside[0])) if i not in inside_joined)
        outLeft = tuple(i for i in range(len(outside[0])) if i not in outside_joined)
        vals = self.create_join_value(outside, outside_joined)
        for i in range(len(inside)):
            if self.get_values(inside[i], inside_joined) in vals:
                outList = vals[self.get_values(inside[i], inside_joined)]
                for j in outList:
                    results.append(self.get_values(outside[j], outLeft) + 
                               self.get_values(inside[i], inLeft) + 
                               self.get_values(outside[j], outside_joined))
        return results, outLeft, inLeft

    def loop_join(self, outside: LookupOutput, inside: LookupOutput, outside_joined: tuple, inside_joined: tuple):
        if outside.size() > inside.size():
            tmp = outside
            outside = inside
            inside = tmp
            
            tmp = outside_joined
            outside_joined = inside_joined
            inside_joined = tmp

        joined_data, outside_left, inside_left = self.loop_join_data(outside.data, inside.data,
                                                                     tuple(outside.header_id(item) for item in outside_joined),
                                                                     tuple(inside.header_id(item) for item in inside_joined)
                                                                     )
        if joined_data is None:
            result = LookupOutput([], [])
        else:
            vals = self.get_values(outside.headers, outside_left) + self.get_values(inside.headers, inside_left) + outside_joined
            result = LookupOutput(vals, joined_data)
            
        for o, i in zip(outside_joined, inside_joined):
            result.insert_alias(i, o)
            
        for alias in outside.alias_map:
            result.insert_alias(alias, outside.alias_map[alias])
            
        for alias in inside.alias_map:
            result.insert_alias(alias, inside.alias_map[alias])
            
        return result
