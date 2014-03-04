package eu.solidcraft.starter.conf

import groovy.transform.TypeChecked

@TypeChecked
class Profiles {
    static final String DEFAULT = 'default';
    static final String PRODUCTION = 'starter.production';
    static final String DEVELOPMENT = 'starter.development';
    static final String TEST = 'starter.test';
}
