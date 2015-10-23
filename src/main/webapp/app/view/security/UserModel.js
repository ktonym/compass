
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
    ,formulas: {
        selectedUser: {
            bind: {
                bindTo: '{usersGrid.selection}',
                deep: true
            },
            get: function(user){
                return user;
            },
            set: function(user){
                user = this.set('selectedUser',user);
            }
        }
    }
});
