module.exports = function(app){
    require('./login')(app);
    require('./menu')(app);
    require('./organizationUnit')(app);
    require('./attachmentType')(app);
    require('./message')(app);
    require('./pagination')(app);
    require('./logout')(app);
    require('./header')(app);
    require('./breadcrumbs')(app);
    require('./requestTypesMenu')(app);
    require('./internalUser')(app);
    require('./externalUser')(app);
    require('./requestTypeAttachmentType')(app);
    require('./capacityDelegation')(app);
    require('./entitledRegistration')(app);
    
};