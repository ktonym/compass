Ext.define("compass.view.security.UserModel",{
    extend: "Ext.app.ViewModel",
    alias: "viewmodel.user",
    stores: {
        users: {
            model: "compass.model.security.User",
            autoLoad: true
        }
    }
});
