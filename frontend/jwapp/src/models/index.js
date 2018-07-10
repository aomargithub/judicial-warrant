module.exports = function(app){
    require('./OrganizationUnit')(app);
    require('./AttachmentType')(app);
    require('./User')(app);
    require('./requestTypeAttachmentType')(app);
    require('./CapacityDelegation')(app);
    require('./EntitledRegistration')(app);
    require('./RequestAttachment')(app);
    require('./Request')(app);
    require('./Entitled')(app);
    require('./EntitledAttachment')(app);
    require('./CapacityDelegationChangeStatusRequest')(app);
    require('./EntitledRegistrationChangeStatusRequest')(app);
    require('./ChangeStatusRequest')(app);
    require('./PasswordChange')(app);
};