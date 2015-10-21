Ext.define('compass.view.base.WindowForm',{

    extend: 'Ext.window.Window',

    alias: 'widget.windowform',

    requires: [
        'compass.util.Util',
        'compass.util.Glyphs',
        'compass.view.base.CancelSaveToolbar'
    ],

    height: 400,

    width: 550,

    autoScroll: true,

    layout: {
        type: 'fit'
    },

    modal: true,

    closable: false,

    bind: {
        title: '{title}',
        glyph: '{glyph}'
    },

    dockedItems: [
        {
            xtype: 'cancel-save-toolbar'
        }
    ]

});
