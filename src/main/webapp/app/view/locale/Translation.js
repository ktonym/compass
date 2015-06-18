Ext.define('compass.view.locale.Translation',{
    extend: 'Ext.button.Split',
    xtype: 'translation',
    requires: ['compass.view.locale.TranslationController'],
    menu: {
        xtype: 'menu',
        defaults: {
           listeners: {
               click: 'onMenuItemClick'
           }
        },
        items: [
            {
                xtype: 'menuitem',
                iconCls: 'en',
                text: 'English'
            },{
                xtype: 'menuitem',
                iconCls: 'ke',
                text: 'Kiswahili'
            },{
                xtype: 'menuitem',
                iconCls: 'fr',
                text: 'Français'
            }
        ]
    }
});