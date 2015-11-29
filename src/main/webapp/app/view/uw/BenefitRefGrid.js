Ext.define('compass.view.uw.BenefitRefGrid',{
    extend: 'compass.view.base.Grid',
    alias: 'widget.benefitref-grid',
    reference: 'benefitRefGrid',
    columns: [
        {
            width: 150,
            dataIndex: "benefitName",
            text: "Benefit Name",
            flex: 1
        },
        {
            width: 150,
            dataIndex: "description",
            text: "Description",
            flex: 2
        }
    ],
    bind: {
        store:  '{benefitrefs}'
    }
});
