# Appends the config.txt used in the Rapsberry PI boot process to adapt Openvario
# relevant changes

do_deploy:append () {

	CONFIG=${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt

     # Openvario 57 LVDS
    if [ "${MACHINE}" = "ov-rpi4-64" ]; then
        
        # Use the machine specific device tree overlay
        echo "# Enable 57 LVDS" >> $CONFIG
        echo "dtoverlay=ov-rpi4-57-lvds" >> $CONFIG
    fi
}
