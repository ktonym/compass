
Ext.define('compass.view.security.UserModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.user',

    stores: {
        users: {
            model: 'compass.model.security.User',
            autoLoad: true
        },
        /**
         * consider handling on the webHandler, /compass/group/* routes
         */
        groups: {
            model: 'compass.model.security.Group',
            autoLoad: true
        }
    }
});
