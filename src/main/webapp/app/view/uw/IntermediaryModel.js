Ext.define('compass.view.uw.IntermediaryModel',{

    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.intermediary',

    stores: {

        intermediaries: {
            model: 'compass.model.uw.Intermediary',
            autoLoad: true
        },

        intermediaryTypes: {
            model: 'compass.model.TextCombo',
            data: [
                ['AGENT'],['AGENCY'],['BROKER']
            ]
        }
    }
});
