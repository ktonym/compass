Ext.define('compass.store.staticData.Members',{

    extend: 'compass.store.staticData.Base',
    model: 'compass.model.uw.Member',
    proxy: {
        type: 'ajax',
        url: 'member/findByScheme.json',
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
