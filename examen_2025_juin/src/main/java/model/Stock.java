package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Stock extends ItemList {

	public List<ItemNode> searchStartWith(String beginning) {
		if (beginning == null || beginning.isBlank()) {
			final List<ItemNode> list = new ArrayList<>(Arrays.asList(toArray()));
			Collections.sort(list);
			return list;
		} else {
			final List<ItemNode> list = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				final ItemNode it = get(i);
				if (it.getItem().getName().startsWith(beginning)) {
					list.add(it);
				}
			}
			Collections.sort(list);
			return list;
		}
	}

}
