Ext.define('compass.view.main.Panel',{
    extend: 'Ext.tab.Panel',
    xtype: 'mainpanel',

    requires: ['compass.view.locale.Translation'],

    activeTab: 0,

    items: [
        {
            xtype: 'panel',
            closable: false,
            iconCls: 'fa fa-home fa-lg tabIcon',
            title: translations.home
        }
    ]

});
