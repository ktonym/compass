Ext.define('compass.view.uw.CorpFormContainer',{

    extend: 'Ext.form.Panel',
    alias: 'widget.corp-form-container',

    items: [
        {
            xtype: 'form',
            reference: 'form',
            bodyPadding: 5,
            modelValidation: true,
            layout: {
                type: 'hbox',
                align: 'stretch'
            },
            items:[
                {
                    xtype: 'fieldset',
                    flex: 1,
                    layout: 'anchor',
                    defaults: {
                       // afterLabelTextTpl: compass.util.Util.required,
                        anchor: '100%',
                        xtype: 'textfield',
                       // msgTarget: 'side',
                        labelWidth: 110
                    },
                    items: [
                        {
                            xtype: 'hiddenfield',
                            name: 'idCorporate',
                            bind: '{currentCorporate.idCorporate}'
                        },
                        {
                            fieldLabel: 'Name',
                            name: 'corporateName',
                            bind: {
                                value: '{currentCorporate.corporateName}',
                                readOnly: '{isEdit}'
                            }
                        },
                        {
                            fieldLabel: 'Abbrev.',
                            name: 'abbreviation',
                            bind: '{currentCorporate.abbreviation}'
                        },
                        {
                            fieldLabel: 'Email',
                            name: 'email',
                            bind: '{currentCorporate.email}'
                        },
                        {
                            fieldLabel: 'Tel.',
                            name: 'tel',
                            bind: '{currentCorporate.tel}'
                        },
                        {
                            fieldLabel: 'Postal Address',
                            name: 'postalAddress',
                            bind: '{currentCorporate.postalAddress}'
                        },
                        {
                            xtype: 'datefield',
                            format: 'Ymd',
                            fieldLabel: 'Join',
                            name: 'joined',
                            bind: '{currentCorporate.joined}'
                        }
                    ]
                }
            ]
        }
    ]


});
