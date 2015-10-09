Ext.define('compass.view.uw.Corporate',{
    extend: 'Ext.panel.Panel',
    xtype: 'corporate',
    requires: [
        'compass.model.uw.*',
        'compass.util.Glyphs',
        'compass.view.uw.CorporatesGrid',
        'compass.view.uw.CorporateModel',
        'compass.view.uw.CorporateController'
    ],

    controller: 'corporate',
    viewmodel: {
        type: 'corporate'
    },

    frame: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'corporates-grid',
            flex: 1
        }
    ],

    dockedItems: [
    {
        xtype: "toolbar",
        dock: "top",
        items: [
            {
                xtype: 'button',
                text: 'Add',
                glyph: compass.util.Glyphs.getGlyph('add'),
                listeners: {
                    click: 'onAdd'
                }

            },
            {
                xtype: 'button',
                text: 'Edit',
                glyph: compass.util.Glyphs.getGlyph('edit'),
                listeners: {
                    click: 'onEdit'
                },
                bind: {
                    disabled: '{!corporatesGrid.selection}'
                }

            },
            {
                xtype: 'button',
                text: 'Delete',
                glyph: compass.util.Glyphs.getGlyph('destroy'),
                listeners: {
                    click: 'onDelete'
                },
                bind: {
                    disabled: '{!corporatesGrid.selection}'
                }

            }
        ]
    }
    ]

});
