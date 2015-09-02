Ext.define("compass.view.menu.Accordion",{
    extend: "Ext.panel.Panel",
    xtype: "mainmenu",
    width: 250,
    layout: {
        type: 'accordion',
        multi: true
    },
    collapsible: true,
    split: true,
    iconCls: 'fa fa-sitemap fa-lg',
    title: translations.menu


//    requires: [
//        "compass.model.menu.Accordion"
//    ],

    //html: "Hello, World!!"
});
