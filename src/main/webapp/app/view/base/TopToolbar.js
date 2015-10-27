Ext.define('compass.view.base.TopToolbar',{

    extend: 'Ext.toolbar.Toolbar',
    xtype: 'top-tool-bar',
    requires: ['compass.util.Glyphs'],

    dock: 'top',
    items: [
        {
            xtype: 'button',
            text: 'Add',
            glyph: compass.util.Glyphs.getGlyph('add'),
            listeners: {
                click: 'onAdd'
            }
        }
    ]

});