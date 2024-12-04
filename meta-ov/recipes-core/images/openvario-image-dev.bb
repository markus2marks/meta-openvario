require openvario-base-image.bb

#IMAGE_ROOTFS_SIZE ?= "3768320"
IMAGE_ROOTFS_SIZE ?= "1048576"

IMAGE_INSTALL += "\
    caltool \
    sensord \
    variod \
    ovmenu-ng \
    i2c-tools \
    raspi-gpio \
"

export IMAGE_BASENAME = "openvario-image"
