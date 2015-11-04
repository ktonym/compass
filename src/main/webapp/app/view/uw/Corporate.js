Ext.define('compass.view.uw.Corporate',{
    extend: 'Ext.panel.Panel',
    xtype: 'corporate',
    requires: [
        'compass.model.uw.*',
        'compass.util.Glyphs',
        'compass.view.base.TopToolbar',
        'compass.view.uw.CorporatesGrid',
        'compass.view.uw.CorporateModel',
        'compass.view.uw.CorporateController',
        'compass.view.uw.CorpAnnivsGrid',
        'compass.view.uw.ContactInfoGrid'
    ],

    controller: 'corporate',

    viewModel: {
        type: 'corporate'
    },

    session: true,

    frame: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'corporates-grid',
            flex: 1
        },
        {
            xtype: 'container',
            split: true,
            layout: {
                type: 'hbox',
                align: 'stretch'
            },
            height: 150,
            items: [{
                xtype: 'corpannivs-grid',
                flex: 2
            },{
                xtype: 'contact-info-grid',
                flex: 1
            }]

        }
    ],

    dockedItems: [
        {
            xtype: "top-tool-bar"
        }
    ]

});
