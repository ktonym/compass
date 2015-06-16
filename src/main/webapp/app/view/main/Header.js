Ext.define('compass.view.main.Header',{
    extend: 'Ext.toolbar.Toolbar',
    xtype: 'appheader',

    //requires: ['compass.view.locale.Translation'],

    ui: 'footer',

    items: [
        {
            xtype: 'component',
            bind: {
                html: '{appHeaderIcon}'
            }
        },{
            xtype: 'component',
            componentCls: 'app-header-title',
            bind: {
                html: '{appName}'
            }
        },{
            xtype: 'tbfill'
        }//,{
         //   xtype: 'translation'
        //},
        ,{
            xtype: 'tbseparator'
        },{
            xtype: 'button',
            itemId: 'logout',
            text: 'Logout',
            reference: 'logout',
            iconCls: 'fa fa-sign-out fa-lg buttonIcon',
            listeners: {
                click: 'onLogout'
            }
        }

    ]

})
