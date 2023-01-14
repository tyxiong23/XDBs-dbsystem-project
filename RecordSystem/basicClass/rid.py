class RID:
    def __init__(self, pageID, slotID):
        self._page = pageID
        self._slot = slotID

    def __str__(self):
        return f'{{page: {self.page}, slot: {self.slot}}}'

    def __hash__(self):
        return hash((self._page, self._slot))

    def __eq__(self, rid):
        if rid is None:
            return False
        else:
            return self._page == rid.page and self._slot == rid.slot
    
    @property
    def page(self):
        return self._page

    @property
    def slot(self):
        return self._slot

    
