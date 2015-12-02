Ext.define('compass.view.uw.IntermediaryWindow', {
    extend: 'compass.view.base.WindowForm',
    xtype: 'intermediary-window',

    height: 360,
    width: 400,

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
            items: [
                {
                    xtype: 'fieldset',
                    flex: 1,
                    title: 'Intermediary Information',
                    layout: 'anchor',
                    defaults: {
                        afterLabelTextTpl: compass.util.Util.required,
                        anchor: '100%',
                        xtype: 'textfield',
                        msgTarget: 'side',
                        labelWidth: 110
                    },
                    items: [
                        {
                            xtype: 'hiddenfield',
                            name: 'idIntermediary',
                            fieldLabel: 'Label',
                            bind: '{currentIntermediary.intermediaryCode}'
                        },
                        {
                            fieldLabel: 'Name',
                            name: 'name',
                            bind: '{currentIntermediary.name}'
                        },
                        {
                            fieldLabel: 'PIN',
                            name: 'pin',
                            bind: '{currentIntermediary.pin}'
                        },
                        {
                            xtype: 'combobox',
                            name: 'type',
                            fieldLabel: 'Type',
                            displayField: 'text',
                            valueField: 'text',
                            queryMode: 'local',
                            bind: {
                                value: '{currentIntermediary.type}',
                                store: '{intermediaryTypes}'
                            }
                        },
                        {
                            xtype: 'datefield',
                            fieldLabel: 'Join Date',
                            name: 'joinDate',
                            format: 'Ymd',
                            bind: '{currentIntermediary.joinDate}'
                        },
                        {
                            fieldLabel: 'Email',
                            name: 'email',
                            bind: '{currentIntermediary.email}'
                        },
                        {
                            fieldLabel: 'Tel',
                            name: 'tel',
                            bind: '{currentIntermediary.tel}'
                        },
                        {
                            fieldLabel: 'Postal Address',
                            name: 'postalAddress',
                            bind: '{currentIntermediary.postalAddress}'
                        },
                        {
                            fieldLabel: 'Street',
                            name: 'street',
                            bind: '{currentIntermediary.street}'
                        },
                        {
                            fieldLabel: 'Town',
                            name: 'town',
                            bind: '{currentIntermediary.town}'
                        }
                    ]
                }
            ]
        }
    ]


});
