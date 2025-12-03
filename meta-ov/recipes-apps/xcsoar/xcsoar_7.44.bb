# Copyright (C) 2014 Unknow User <unknow@user.org>
# Released under the MIT license (see COPYING.MIT for the terms)

PR="r0"
RCONFLICTS:${PN}="xcsoar-testing"

SRC_URI = "git://github.com/markus2marks/XCSoar.git;protocol=https;branch=canaerospace \
"
SRCREV = "aa2a5f046153917ac657b02a766dbbeb242459a6"

require xcsoar.inc
