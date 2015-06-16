/**
 * This class is the main view for the application. It is specified in app.js as the
 * "autoCreateViewport" property. That setting automatically applies the "viewport"
 * plugin to promote that instance of this class to the body element.
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('compass.view.main.Main', {
    extend: 'Ext.container.Container',
    plugins: 'viewport',
    xtype: 'app-main',

    requires: [
        'compass.view.main.Header',
        'compass.view.main.Footer',
        'compass.view.main.Panel',
        'compass.view.main.MainController',
        'compass.view.main.MainModel'
    ],

    controller: 'main',
    viewModel: {
        type: 'main'
    },

    layout: {
        type: 'border'
    },

    items: [
        {
            region: 'center',
            xtype: 'mainpanel'
        },{
            region: 'north',
            xtype: 'appheader'
        },{
            region: 'south',
            xtype: 'appfooter'
        },{
            region: 'west',
            xtype: 'container',
            width: 200,
            split: true
        }
    ]
});
