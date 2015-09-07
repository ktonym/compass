
Ext.define("compass.view.security.User",{
    extend: "Ext.panel.Panel",
    xtype: "user",
 
    requires: [
        "compass.view.security.UsersGrid",
        "compass.util.Glyphs"
    ],
    
    controller: "user",
    viewModel: {
        type: "user"
    },

    frame: true,

    layout: {

        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'users-grid',
            flex: 1
//#7
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
                    }

                },
                {
                    xtype: 'button',
                    text: 'Delete',
                    glyph: compass.util.Glyphs.getGlyph('destroy'),
                    listeners: {
                        click: 'onDelete'
                    }

                }
            ]
        }
    ]

});