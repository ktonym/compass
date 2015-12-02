Ext.define('compass.view.uw.CorporateModel',{

    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.corporate',

    stores: {
        corporates: {
            model: 'compass.model.uw.Corporate',
            autoLoad: true,
            session: true
        },
        contactinfos: {
            model: 'compass.model.uw.ContactInfo',
            proxy: {
                type : 'ajax',
                url  : 'compass/uw/contactinfo/findAll.json',
                extraParams: {
                    idCorporate: '{idCorporate}'
                },
                reader: {
                    type            : 'json',
                    rootProperty    : 'data'//,
                    //totalProperty   : 'results'
                }
            },
            autoLoad: true,
            session: true
        },
        annivs: {
            model: 'compass.model.uw.CorpAnniv',
            proxy: {
                type : 'ajax',
                url  : 'compass/uw/anniv/findAll.json',
                extraParams: {
                    idCorporate: '{idCorporate}'
                },
                reader: {
                    type            : 'json',
                    rootProperty    : 'data'
                }
            },
            autoLoad: true,
            session: true
        }

    }/*,

    formulas: {
        currentCorporate: {
            bind: {
                bindTo: '{corporatesGrid.selection}',
                deep: true
            },
            get: function(corporate){
                return corporate;
            },
            set: function(corporate){
                corporate = this.set('currentCorporate',corporate);
            }
        }
    }*/

});
