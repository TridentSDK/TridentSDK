#!/usr/bin/env python3

# Script created by Nick Robson (@nickrobson)

import html
import sys

import blocks as ids_blocks
import items as ids_items

from collections import OrderedDict

def main(package_name, class_name):
    block_data = ids_blocks.get_blocks_data()
    blocks = block_data['blocks']
    blocks_sup_codes = block_data['sup_codes']

    item_data = ids_items.get_items_data()
    items = item_data['items']
    items_sup_codes = item_data['sup_codes']

    sup_codes = {}
    sup_codes.update(blocks_sup_codes)
    sup_codes.update(items_sup_codes)

    unknown_codes = set(sup_codes) - set(map(lambda field: field[0], fields))
    if len(unknown_codes):
        print('Unknown sup codes:')
        for code in unknown_codes:
            print(code, ':', sup_codes[code])
        return

    with open('generate_template.java') as f:
        template = f.read()

    blocks_string = ''
    for i, block in enumerate(sorted(blocks, key = lambda b: b['id'])):
        enum_name = conflict_resolutions.get(block['id'], to_enum_name(block['name']))
        sup_params = sups_to_params(block['sup'])
        blocks_string += f'    {enum_name}({block["id"]}, "{block["string_id"]}", "{block["name"]}", {sup_params}),\n'

    last_block_id = block['id']

    items_string = ''
    for i, item in enumerate(sorted(items, key = lambda i: i['id'])):
        enum_name = conflict_resolutions.get(item['id'], to_enum_name(item['name']))
        sup_params = sups_to_params(item['sup'])
        eol = ',' if i != len(items) - 1 else ';'
        items_string += f'    {enum_name}({item["id"]}, "{item["string_id"]}", "{item["name"]}", {sup_params}){eol}\n'

    fields_string = ''
    methods_string = ''
    for _, type, fname, field_doc, method_doc in fields:
        mname = fname
        if type != 'boolean':
            mname = 'get' + fname[0].upper() + fname[1:]
        if fields_string != '':
            fields_string +=  '\n'
        fields_string +=  '    /**\n'
        fields_string +=  '     * ' + html.escape(field_doc.format(class_name = class_name)) + '\n'
        fields_string +=  '     */\n'
        fields_string += f'    private final {type} {fname};\n'

        if methods_string != '':
            methods_string +=  '\n'
        methods_string +=  '    /**\n'
        methods_string +=  '     * ' + html.escape(method_doc.format(class_name = class_name)) + '\n'
        methods_string +=  '     *\n'
        methods_string +=  '     * @return ' + html.escape(field_doc.format(class_name = class_name)) + '\n'
        methods_string +=  '     */\n'
        methods_string += f'    public {type} {mname}() {{\n'
        methods_string += f'        return this.{fname};\n'
        methods_string +=  '    }\n'

    constructor_string = ''
    params = ', '.join(map(lambda field: f'{field[1]} {field[2]}', fields))
    constructor_string += f'    private {class_name}({params}) {{\n'
    for _, _, fname, _, _ in fields:
        constructor_string += f'        this.{fname} = {fname};\n'
    constructor_string +=  '    }\n'

    is_item_method = ''
    is_item_method +=  '    /**\n'
    is_item_method += f'     * Gets if this {class_name} is a block.\n'
    is_item_method +=  '     *\n'
    is_item_method +=  '     * @return true iff it is a block\n'
    is_item_method +=  '     */\n'
    is_item_method +=  '    public boolean isBlock() {\n'
    is_item_method += f'        return this.id <= {last_block_id}; // generated\n'
    is_item_method +=  '    }\n'
    is_item_method +=  '\n'
    is_item_method +=  '    /**\n'
    is_item_method += f'     * Gets if this {class_name} is an item.\n'
    is_item_method +=  '     *\n'
    is_item_method +=  '     * @return true iff it is an item\n'
    is_item_method +=  '     */\n'
    is_item_method +=  '    public boolean isItem() {\n'
    is_item_method +=  '        return !this.isBlock();\n'
    is_item_method +=  '    }'

    output = template.format(
        package_name = package_name,
        class_name = class_name,
        blocks = blocks_string,
        items = items_string,
        fields = fields_string,
        methods = methods_string,
        constructor = constructor_string,
        is_item_method = is_item_method
    )

    print(output)

fields = [
    ('_', 'int', 'id', "The {class_name}'s numeric ID", "Gets the {class_name}'s numeric ID."),
    ('_', 'String', 'stringId', "The {class_name}'s string ID", "Gets the {class_name}'s string ID."),
    ('_', 'String', 'displayName', "The {class_name}'s display name", "Gets the {class_name}'s display name"),
    ('S', 'boolean', 'needsData', 'Whether this {class_name} needs data.', "Gets whether additional data from the saved game's Data array is required to fully define the {class_name}."), # blocks
    ('B', 'boolean', 'needsDamage', 'Whether this {class_name} needs damage.', "Gets whether additional data in the {class_name}'s Damage field is required to fully define the inventory item."), # blocks and items
    ('E', 'boolean', 'needsBlockEntity', 'Whether this {class_name} needs a block entity.', 'Gets whether a block entity is required to store additional data about the {class_name}.'), # blocks
    ('I', 'boolean', 'hasSeparateItemID', 'Whether this {class_name} has a different ID in item form to when in block form.', 'Gets whether this {class_name} has a separate ID when it an inventory item.'), # blocks
    ('D', 'boolean', 'hasDurability', 'Whether this {class_name} has durability.', "Gets whether this {class_name} uses the Damage field to define its durability."), # items
]

def to_enum_name(string):
    string = string.upper()
    string = string.replace(' ', '_')
    string = string.replace("'", '_')
    return string

def sups_to_params(sups):
    params = []
    for s, *_ in fields:
        if s != '_':
            params.append(str(s in sups).lower())
    return ', '.join(params)

conflict_resolutions = {
    8: 'FLOWING_WATER',
    10: 'FLOWING_LAVA',
    59: 'WHEAT_CROP',
    68: 'WALL_SIGN',
    74: 'LIT_REDSTONE_ORE',
    76: 'LIT_REDSTONE_TORCH',
    78: 'SNOW_LAYER',
    94: 'LIT_REDSTONE_REPEATER',
    99: 'BROWN_MUSHROOM_BLOCK',
    100: 'RED_MUSHROOM_BLOCK',
    115: 'NETHER_WART_CROP',
    124: 'LIT_REDSTONE_LAMP',
    141: 'CARROT_CROP',
    142: 'POTATO_CROP',
    147: 'LIGHT_WEIGHTED_PRESSURE_PLATE',
    148: 'HEAVY_WEIGHTED_PRESSURE_PLATE',
    150: 'LIT_REDSTONE_COMPARATOR',
    161: 'LEAVES2',
    162: 'WOOD2',
    177: 'WALL_BANNER',
    207: 'BEETROOT_SEEDS_CROP',
    323: 'SIGN_ITEM',
    324: 'OAK_DOOR_ITEM',
    330: 'IRON_DOOR_ITEM',
    337: 'CLAY_BALL',
    338: 'SUGAR_CANE_ITEM',
    354: 'CAKE_ITEM',
    355: 'BED_ITEM',
    356: 'REDSTONE_REPEATER_ITEM',
    360: 'MELON_ITEM',
    379: 'BREWING_STAND_ITEM',
    380: 'CAULDRON_ITEM',
    390: 'FLOWER_POT_ITEM',
    397: 'MOB_HEAD_ITEM',
    404: 'REDSTONE_COMPARATOR_ITEM',
    405: 'NETHER_BRICK_ITEM',
    414: 'RABBIT_FOOT',
    425: 'BANNER_ITEM',
    427: 'SPRUCE_DOOR_ITEM',
    428: 'BIRCH_DOOR_ITEM',
    429: 'JUNGLE_DOOR_ITEM',
    430: 'ACACIA_DOOR_ITEM',
    431: 'DARK_OAK_DOOR_ITEM',
    437: 'DRAGONS_BREATH',
    2256: 'DISC_13',
    2266: 'DISC_11',
}

if __name__ == '__main__':
    if len(sys.argv) > 1:
        package_name = sys.argv[1]
        class_name = sys.argv[2]
    else:
        package_name = 'net.tridentsdk.base'
        class_name = 'Substance'
    main(package_name, class_name)
