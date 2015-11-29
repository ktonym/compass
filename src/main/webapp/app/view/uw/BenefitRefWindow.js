Ext.define('compass.view.uw.BenefitRefWindow', {
    extend: 'compass.view.base.WindowForm',
    xtype: 'benefitref-window',

    height: 220,
    width: 500,

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
                    title: 'Benefit Information',
                    layout: 'anchor',
                    defaults: {
                        afterLabelTextTpl: compass.util.Util.required,
                        anchor: '100%',
                        xtype: 'textfield',
                        msgTarget: 'side',
                        labelWidth: 95
                    },
                    items: [
                        {
                            xtype: 'hiddenfield',
                            name: 'benefitCode',
                            fieldLabel: 'Label',
                            bind: '{currentBenefitRef.benefitCode}'
                        },
                        {
                            fieldLabel: 'Benefit Name',
                            name: 'benefitName',
                            bind: '{currentBenefitRef.benefitName}'
                        },
                        {
                            xtype: 'textareafield',
                            fieldLabel: 'Description',
                            name: 'description',
                            bind: '{currentBenefitRef.description}'
                        }
                    ]
                }
            ]
        }
    ]


});
