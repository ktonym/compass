Ext.define('compass.store.staticData.Principals',{

    extend: 'compass.store.staticData.Base',
    model: 'compass.model.uw.Principal',
    proxy: {
        type: 'ajax',
        url: 'principal/findByScheme.json',
        reader: {
            type: 'json',root:'data'
        }
    },
    doFindByScheme: function(idCorporate){
        this.load({
            params: {
                idCorporate: idCorporate
            }
        });
    }
});
