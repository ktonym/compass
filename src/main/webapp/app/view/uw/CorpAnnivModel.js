Ext.define('compass.view.uw.CorpAnnivModel',{
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.corpanniv',
    stores: {
        corpannivs: {
            model: 'compass.model.uw.CorpAnniv',
            autoLoad: true,
            session: true
        }
    }
});