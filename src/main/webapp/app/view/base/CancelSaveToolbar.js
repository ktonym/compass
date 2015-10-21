Ext.define('compass.view.base.CancelSaveToolbar',{

    extend: 'Ext.toolbar.Toolbar',

    xtype: 'cancel-save-toolbar',

    requires: [
        'compass.util.Glyphs'
    ],

    dock: 'bottom',

    ui: 'footer',

    layout: {
        pack: 'end',
        type: 'hbox'
    },

    items: [
        {
            xtype: 'button',
            text: 'Save',
            glyph: compass.util.Glyphs.getGlyph('save'),
            listeners: {
                click: 'onSave'
            }
        }
    ]

});
