do_deploy:append () {

	CONFIG=${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt

     # Openvario 57 LVDS
    if [ "${MACHINE}" = "ov-rpi4-64" ]; then
        echo "# Enable 57 LVDS" >> $CONFIG
        echo "dtoverlay=ov-rpi4-57-lvds-overlay" >> $CONFIG
        echo "dtoverlay=i2c1,pins_44_45=1" >> $CONFIG
    fi
}
