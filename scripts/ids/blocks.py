#!/usr/bin/env python3

import bs4
import json
import urllib.request

from collections import OrderedDict

url = 'http://minecraft.gamepedia.com/api.php?action=parse&format=json&prop=text&title=Data_values&text=%7B%7B%3AData+values%2FBlock+IDs%7D%7D'

def get_blocks_data():
    req = urllib.request.Request(url)
    req.headers['User-Agent'] = 'Trident/BlockID Getter'

    with urllib.request.urlopen(req) as u:
        content = u.read()

    json_content = json.loads(content)
    table_content = json_content['parse']['text']['*']

    soup = bs4.BeautifulSoup(table_content, 'html.parser')

    blocks = []
    known_sups = {
        'S': "Requires additional data from the saved game's Data array to fully define the block.",
        'B': "Requires additional data in the item's Damage field to fully define the inventory item.",
        'E': 'Requires a block entity to store additional data.',
        'I': 'Has a separate ID as an inventory item.'
    }

    for table in soup.find_all('table'):
        for table_row in table.find_all('tr')[1:]:
            tds = table_row.find_all('td')
            numeric_id = int(tds[1].get_text())
            string_id = tds[3].get_text()
            name = tds[4].find('a').get_text()
            sup_data = []
            for span_tag in tds[4].select('sup a span'):
                sup_code = span_tag.get_text()
                if sup_code not in known_sups:
                    sup_description = span_tag['title']
                    known_sups[sup_code] = sup_description
                sup_data.append(sup_code)
            blocks.append({
                    'id': numeric_id,
                    'string_id': string_id,
                    'name': name,
                    'sup': sup_data
                })

    return OrderedDict([
        ('blocks', blocks),
        ('sup_codes', known_sups),
    ])

if __name__ == '__main__':
    print(json.dumps(get_blocks_data()))
