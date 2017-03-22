#!/usr/bin/env python3

import bs4
import json
import urllib.request

url = '''
http://minecraft.gamepedia.com/api.php?action=parse&format=json&prop=text&title=Data_values&text=%7B%7B%3AData+values%2FBlock+IDs%7D%7D
'''.strip()

req = urllib.request.Request(url)
req.headers['User-Agent'] = 'Trident/BlockID Getter'

with urllib.request.urlopen(req) as u:
    content = u.read()

json_content = json.loads(content)
table_content = json_content['parse']['text']['*']

soup = bs4.BeautifulSoup(table_content, 'html.parser')

blocks = []

for table in soup.find_all('table'):
    for table_row in table.find_all('tr')[1:]:
        tds = table_row.find_all('td')
        id = int(tds[1].get_text())
        string_id = tds[3].get_text()
        name = tds[4].find('a').get_text()
        blocks.append({
                'id': id,
                'string_id': string_id,
                'name': name
            })

print(blocks)