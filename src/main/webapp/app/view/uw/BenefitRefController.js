Ext.define('compass.view.uw.BenefitRefController',{

    extend: 'compass.view.base.ViewController',
    alias: 'controller.benefitref',
    requires: ['compass.util.Util'],

    createDialog: function(record){
        var me = this,
            view = me.getView(),
            glyphs = compass.util.Glyphs;
        me.isEdit = !!record;
        me.dialog = view.add({
            xtype: 'benefitref-window',
            viewModel: {
                data: {
                    title: record ? 'Edit: ' + record.get('benefitName') :
                        'Add Benefit',
                    glyph: record ? glyphs.getGlyph('edit') :
                        glyphs.getGlyph('add')
                },
                links: {
                    currentBenefitRef: record  || {
                        type: 'BenefitRef',
                        create: true
                    }
                }
            },
            session: true
        });
        me.dialog.show();
    },

    onSave: function(button, e, options){
        var me = this,
            dialog = me.dialog,
            form = me.lookupReference('form'),
            isEdit = me.isEdit;//,
            //session = me.getSession(),
            //id;
        if (form.isValid()) {
            //if (!isEdit) {
            //    id = dialog.getViewModel().get('currentBenefitRef').idBenefitRef;
            //}
            //
            //dialog.getSession().save();
            //
            //if (!isEdit) {
            //    me.getStore('benefitrefs').add(session.getRecord('BenefitRef',id));
            //}
            //if(!isEdit){
            //    var idField = form.down("benefitCode").setValue(null);
            //    //idField = null;
            //}
            console.log(Ext.util.JSON.encode(form.getValues()));
            Ext.Ajax.request({
                //clientValidation: true,
                url: 'compass/benefitref/store.json',
                scope: me,
                params : {
                    "data" : Ext.util.JSON.encode(form.getValues())
                },
                success: 'onSaveSuccess',
                failure: 'onSaveFailure'
            });

            //me.onCancel();
        }

        //var batch = session.getSaveBatch();
        //if (batch){
        //    batch.start();
        //}
    },
    onSaveSuccess: function(conn, response, options, eOpts) {
        var me=this,
            result = compass.util.Util.decodeJSON(conn.responseText);
        if (result.success) {
            compass.util.Util.showToast('Success! Benefit saved.');
            me.onCancel();
            me.refresh();
        } else {
            compass.util.Util.showErrorMsg(result.msg);
        }
    },
    onSaveFailure: function(conn, response, options, eOpts){
        compass.util.Util.showErrorMsg(conn.responseText);
    },
    refresh: function(button,e,options){
        var me = this,
            store = me.getStore('benefitrefs');
        store.load();
    }


});
