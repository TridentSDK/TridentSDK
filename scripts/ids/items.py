#!/usr/bin/env python3

import bs4
import json
import urllib.request

from collections import OrderedDict

url = '''
http://minecraft.gamepedia.com/api.php?action=parse&format=json&prop=text&title=Data_values&text=%7B%7B%3AData+values%2FItem+IDs%7D%7D
'''.strip()

def get_items_data():
    req = urllib.request.Request(url)
    req.headers['User-Agent'] = 'Trident/ItemID Getter'

    with urllib.request.urlopen(req) as u:
        content = u.read()

    json_content = json.loads(content)
    table_content = json_content['parse']['text']['*']

    soup = bs4.BeautifulSoup(table_content, 'html.parser')

    items = []
    known_sups = {
        'D': "Use the item's Damage field to define its durability.",
        'B': "Requires additional data in the item's Damage field to fully define the inventory item."
    }

    for table in soup.find_all('table'):
        for table_row in table.find_all('tr')[1:]:
            tds = table_row.find_all('td')
            id = int(tds[1].get_text())
            string_id = tds[3].get_text()
            a_tag = tds[4].find('a')
            if a_tag is None:
                continue
            name = a_tag.get_text()
            sup_data = []
            for span_tag in tds[4].select('sup a span'):
                sup_code = span_tag.get_text()
                if sup_code not in known_sups:
                    sup_description = span_tag['title']
                    known_sups[sup_code] = sup_description
                sup_data.append(sup_code)
            items.append({
                    'id': id,
                    'string_id': string_id,
                    'name': name,
                    'sup': sup_data
                })

    return OrderedDict([
        ('items', items),
        ('sup_codes', known_sups),
    ])

if __name__ == '__main__':
    print(json.dumps(get_items_data()))
