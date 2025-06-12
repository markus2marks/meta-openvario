inherit image_types

# For the names of kernel artifacts
inherit kernel-artifact-names


do_image_complete () {
    
    echo "Listing all .wic.bz2 files in the deploy directory:"
    ls -1 ${IMGDEPLOYDIR}/*.wic.bz2 2>/dev/null || echo "No .wic.bz2 files found"

    WIC_BZ2=$(ls -t ${IMGDEPLOYDIR}/*.wic.bz2 2>/dev/null | head -n 1)
    echo "Using WIC file: $WIC_BZ2"

    IMG="${IMGDEPLOYDIR}/${IMAGE_NAME}.img"
    LINK_NAME="${IMGDEPLOYDIR}/openvario-image.img"  # Set symlink name here

    if [ -f "$WIC_BZ2" ]; then
        echo "Deleting old .img files except the new one:"
        find "${IMGDEPLOYDIR}" -maxdepth 1 -type f -name "*.img" ! -name "$(basename "$IMG")" -exec rm -v {} \;

        echo "Extracting $WIC_BZ2 to $IMG"
        bzip2 -dc "$WIC_BZ2" > "$IMG"

        [ -L "$LINK_NAME" ] && rm "$LINK_NAME"
        ln -s "$(basename "$IMG")" "$LINK_NAME"
        echo "Symlink created: $LINK_NAME -> $(readlink "$LINK_NAME")"
    else
        echo "WARNING: $WIC_BZ2 not found – skipping .img creation"
    fi
    
    
}
