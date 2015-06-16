/**
 * This class is the view model for the Main view of the application.
 */
Ext.define('compass.view.main.MainModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.main',

    data: {
        name: 'compass',
        appName: 'Compass',
        appHeaderIcon: '<span class="fa fa-compass fa-lg app-header-logo">',
        footer: 'Compass Medical Insurance System - Turbosoft - ' +
            'http://turbosoft.co.ke'
    }

    //TODO - add data, formulas and/or methods to support your view
});