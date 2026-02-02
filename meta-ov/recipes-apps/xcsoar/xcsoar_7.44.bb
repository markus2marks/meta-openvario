# Copyright (C) 2014 Unknow User <unknow@user.org>
# Released under the MIT license (see COPYING.MIT for the terms)

PR="r0"
RCONFLICTS:${PN}="xcsoar-testing"

SRC_URI = "git://github.com/markus2marks/XCSoar.git;protocol=https;branch=canaerospace \
"
SRCREV = "32ac5c90d5846c89e2f5a4678b5e0366dd7fcbd5"

require xcsoar.inc
