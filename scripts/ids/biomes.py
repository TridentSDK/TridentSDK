#!/usr/bin/env python3

import bs4
import json
import urllib.request

url = '''
http://minecraft.gamepedia.com/Data_values
'''.strip()

req = urllib.request.Request(url)
req.headers['User-Agent'] = 'Trident/BiomeID Getter'

with urllib.request.urlopen(req) as u:
    content = u.read().decode('utf-8')

soup = bs4.BeautifulSoup(content, 'html.parser')

biome_ids_tag = soup.find('span', {'id': 'Biome_IDs'})
biome_ids_table = biome_ids_tag.parent.next_sibling.next_sibling.next_sibling.next_sibling

biomes = []

for table_row in biome_ids_table.find_all('tr')[1:]:
    tds = table_row.find_all('td')
    tds = list(map(lambda td: td.get_text(), tds))
    try:
        a_id, a_name = tds[0], tds[1]
        if a_id != 'N/A':
            if a_id[0] == '(':
                a_id = a_id[1:-1]
            biomes.append([int(a_id), a_name])
    except:
        pass
    try:
        b_id, b_name = tds[-2], tds[-1]
        if b_id != 'N/A':
            if b_id[0] == '(':
                b_id = b_id[1:-1]
            biomes.append([int(b_id), b_name])
    except:
        pass

print(sorted(biomes))