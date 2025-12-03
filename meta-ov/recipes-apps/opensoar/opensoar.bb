# Copyright (C) 2014 Unknow User <unknow@user.org>
# Released under the MIT license (see COPYING.MIT for the terms)

PR="r23.2"
# RCONFLICTS:${PN}="opensoar-dev"

require openvario.inc

SRC_URI = "git://github.com/OpenSoaring/OpenSoar.git;protocol=https;branch=master " 
## # OpenSoar Tag: v7.42.22
## # SRCREV = "1ac45834b293d306164e2059657fc0c7d10633c2"
## # prev. (and old) Tag: 
## 
SRCREV = "8d1d8f1976291ac8a4e03070594690908f64034e"



# dev branch is: boost 1.87:
BOOST_VERSION = "1.87.0"
BOOST_SHA256HASH = "af57be25cb4c4f4b413ed692fe378affb4352ea50fbe294a11ef548f4d527d89"

require opensoar.inc

