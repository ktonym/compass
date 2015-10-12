Ext.define('compass.view.uw.BenefitRefModel',{

    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.benefitref',

    stores: {

        benefitrefs: {
            model: 'compass.model.uw.BenefitRef',
            autoLoad: true
        }

    },

    formulas: {
        currentBenefitRef: {
            bind: {
                bindTo: '{benefitRefGrid.selection}',
                deep: true
            },
            get: function(benefit){
                return benefit;
            },
            set: function(benefit){
                benefit = this.set('currentBenefitRef',benefit);
            }
        }
    }

});
