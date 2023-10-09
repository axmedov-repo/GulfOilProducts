package com.axmedov.gulfapp.utils

import com.axmedov.gulfapp.R
import com.axmedov.gulfapp.data.entities.NewOilData
import com.axmedov.gulfapp.data.entities.OilData
import com.axmedov.gulfapp.data.entities.ProductData
import com.axmedov.gulfapp.data.entities.SubOilData
import com.axmedov.gulfapp.data.entities.SubProductData
import com.axmedov.gulfapp.data.enums.ProductTypes

val subProductsList = listOf<SubProductData>(
    SubProductData(
        id = 0, name = "Cars"
    ),
    SubProductData(
        id = 1, name = "Motorcycle & Scooter"
    ),
    SubProductData(
        id = 2, name = "Commercial Vehicle"
    ),
    SubProductData(
        id = 3, name = "Industrial"
    )
)

val oilsList = listOf<OilData>(
    OilData(
        id = 0, name = "Cars", image = R.drawable.ic_car
    ),
    OilData(
        id = 1, name = "Motorcycle & Scooter", image = R.drawable.ic_motorcycle
    ),
    OilData(
        id = 2, name = "Commercial Vehicle", image = R.drawable.ic_commercial_cars
    ),
    OilData(
        id = 3, name = "Industrial", image = R.drawable.ic_industrial_cars
    )
)

val subOilsList = listOf<SubOilData>(
    SubOilData(
        id = 0, name = "Cars", image = R.drawable.ic_car
    ),
    SubOilData(
        id = 1, name = "Motorcycle & Scooter", image = R.drawable.ic_motorcycle
    ),
    SubOilData(
        id = 2, name = "Commercial Vehicle", image = R.drawable.ic_commercial_cars
    ),
    SubOilData(
        id = 3, name = "Industrial", image = R.drawable.ic_industrial_cars
    )
)

val newOilsList = listOf<NewOilData>(
    NewOilData(
        0,
        "Gulfmar Supreme",
        "High Speed Marine Diesel Oils",
        "https://euassets.gulfoilltd.com/PDS-DPD/pds/Gulfmar%20Supreme_PDS_122.pdf"
    ),
    NewOilData(
        1,
        "Gulfmar Select Plus",
        "Trunk Piston Engine Oils",
        "https://euassets.gulfoilltd.com/PDS-DPD/pds/Gulfmar%20Select%20Plus_PDS_2.pdf"
    ),
    NewOilData(
        2,
        "Gulfmar Select",
        "Trunk Piston Engine Oils",
        "https://euassets.gulfoilltd.com/PDS-DPD/pds/Gulfmar%20Select_PDS_4.pdf"
    ),
    NewOilData(
        3,
        "Gulfmar MX",
        "High Speed Marine Diesel Oils",
        "https://euassets.gulfoilltd.com/PDS-DPD/pds/Gulfmar%20MX_PDS_124.pdf"
    ),
    NewOilData(
        4,
        "Gulfmar HP",
        "High Speed Marine Diesel Oils",
        "https://euassets.gulfoilltd.com/PDS-DPD/pds/Gulfmar%20HP_PDS_123.pdf"
    ),
    NewOilData(
        5,
        "Gulfmar DPO Plus",
        "Trunk Piston Engine Oils",
        "https://euassets.gulfoilltd.com/PDS-DPD/pds/Gulfmar%20DPO%20Plus_PDS_13.pdf"
    ),
    NewOilData(
        6,
        "Gulfmar DDC",
        "High Speed Marine Diesel Oils",
        "https://euassets.gulfoilltd.com/PDS-DPD/pds/Gulfmar%20DDC_PDS_18.pdf"
    ),
    NewOilData(
        7,
        "Gulfmar Cyloil LS",
        "Cylinder Oils",
        "https://euassets.gulfoilltd.com/PDS-DPD/pds/Gulfmar%20Cyloil%20LS_PDS_126.pdf"
    ),
    NewOilData(
        8, "Gulfmar Cyloil", "Cylinder Oils", "https://euassets.gulfoilltd.com/PDS-DPD/pds/Gulfmar%20Cyloil_PDS_180.pdf"
    ),
    NewOilData(
        9,
        "Gulfmar AL",
        "Trunk Piston Engine Oils",
        "https://euassets.gulfoilltd.com/PDS-DPD/pds/Gulfmar%20AL%20415_PDS_154_2.pdf"
    ),
    NewOilData(
        10, "", "", ""
    ),
    NewOilData(
        11, "", "", ""
    ),
    NewOilData(
        12, "", "", ""
    ),
    NewOilData(
        13, "", "", ""
    ),
    NewOilData(
        14, "", "", ""
    ),
    NewOilData(
        15, "", "", ""
    ),
    NewOilData(
        16, "", "", ""
    ),
    NewOilData(
        17, "", "", ""
    ),
    NewOilData(
        18, "", "", ""
    ),
    NewOilData(
        19, "", "", ""
    ),
    NewOilData(
        20, "", "", ""
    ),
    NewOilData(
        21, "", "", ""
    ),
    NewOilData(
        22, "", "", ""
    ),
    NewOilData(
        23, "", "", ""
    ),
    NewOilData(
        24, "", "", ""
    ),
    NewOilData(
        25, "", "", ""
    ),
    NewOilData(
        26, "", "", ""
    ),
    NewOilData(
        27, "", "", ""
    ),
    NewOilData(
        28, "", "", ""
    ),
    NewOilData(
        29, "", "", ""
    ),
    NewOilData(
        30, "", "", ""
    ),
    NewOilData(
        31, "", "", ""
    ),
    NewOilData(
        32, "", "", ""
    ),
    NewOilData(
        33, "", "", ""
    ),
    NewOilData(
        34, "", "", ""
    ),
    NewOilData(
        35, "", "", ""
    ),
    NewOilData(
        36, "", "", ""
    ),
    NewOilData(
        37, "", "", ""
    ),
    NewOilData(
        38, "", "", ""
    ),
    NewOilData(
        39, "", "", ""
    ),
    NewOilData(
        40, "", "", ""
    ),
    NewOilData(
        41, "", "", ""
    ),
    NewOilData(
        42, "", "", ""
    ),
    NewOilData(
        43, "", "", ""
    ),
    NewOilData(
        44, "", "", ""
    ),
    NewOilData(
        45, "", "", ""
    ),
    NewOilData(
        46, "", "", ""
    ),
    NewOilData(
        47, "", "", ""
    ),
    NewOilData(
        48, "", "", ""
    ),
    NewOilData(
        49, "", "", ""
    ),
    NewOilData(
        50, "", "", ""
    ),
    NewOilData(
        51, "", "", ""
    ),
    NewOilData(
        52, "", "", ""
    ),
    NewOilData(
        53, "", "", ""
    ),
    NewOilData(
        54, "", "", ""
    ),
    NewOilData(
        55, "", "", ""
    ),
    NewOilData(
        56, "", "", ""
    ),
    NewOilData(
        57, "", "", ""
    ),
    NewOilData(
        58, "", "", ""
    ),
    NewOilData(
        59, "", "", ""
    ),
    NewOilData(
        60, "", "", ""
    ),
    NewOilData(
        61, "", "", ""
    ),
    NewOilData(
        62, "", "", ""
    ),
    NewOilData(
        63, "", "", ""
    ),
    NewOilData(
        64, "", "", ""
    ),
    NewOilData(
        65, "", "", ""
    ),
    NewOilData(
        66, "", "", ""
    ),
    NewOilData(
        67, "", "", ""
    ),
    NewOilData(
        68, "", "", ""
    ),
    NewOilData(
        69, "", "", ""
    ),
    NewOilData(
        70, "", "", ""
    ),
    NewOilData(
        71, "", "", ""
    ),
    NewOilData(
        72, "", "", ""
    ),
    NewOilData(
        73, "", "", ""
    ),
    NewOilData(
        74, "", "", ""
    ),
    NewOilData(
        75, "", "", ""
    ),
    NewOilData(
        76, "", "", ""
    ),
    NewOilData(
        77, "", "", ""
    ),
    NewOilData(
        78, "", "", ""
    ),
    NewOilData(
        79, "", "", ""
    ),
    NewOilData(
        80, "", "", ""
    ),
    NewOilData(
        81, "", "", ""
    ),
    NewOilData(
        82, "", "", ""
    ),
    NewOilData(
        83, "", "", ""
    ),
    NewOilData(
        84, "", "", ""
    ),
    NewOilData(
        85, "", "", ""
    ),
    NewOilData(
        86, "", "", ""
    ),
    NewOilData(
        87, "", "", ""
    ),
    NewOilData(
        88, "", "", ""
    ),
    NewOilData(
        89, "", "", ""
    ),
    NewOilData(
        90, "", "", ""
    ),
    NewOilData(
        91, "", "", ""
    ),
    NewOilData(
        92, "", "", ""
    ),
    NewOilData(
        93, "", "", ""
    ),
    NewOilData(
        94, "", "", ""
    ),
    NewOilData(
        95, "", "", ""
    ),
    NewOilData(
        96, "", "", ""
    ),
    NewOilData(
        97, "", "", ""
    ),
    NewOilData(
        98, "", "", ""
    ),
    NewOilData(
        99, "", "", ""
    ),
    NewOilData(
        100, "", "", ""
    ),
    NewOilData(
        101, "", "", ""
    ),
    NewOilData(
        102, "", "", ""
    ),
    NewOilData(
        103, "", "", ""
    ),
    NewOilData(
        104, "", "", ""
    ),
    NewOilData(
        105, "", "", ""
    ),
    NewOilData(
        106, "", "", ""
    ),
    NewOilData(
        107, "", "", ""
    ),
    NewOilData(
        108, "", "", ""
    ),
    NewOilData(
        109, "", "", ""
    ),
    NewOilData(
        110, "", "", ""
    ),
    NewOilData(
        111, "", "", ""
    ),
    NewOilData(
        112, "", "", ""
    ),
    NewOilData(
        113, "", "", ""
    ),
    NewOilData(
        114, "", "", ""
    ),
    NewOilData(
        115, "", "", ""
    ),
    NewOilData(
        116, "", "", ""
    ),
    NewOilData(
        117, "", "", ""
    ),
    NewOilData(
        118, "", "", ""
    ),
    NewOilData(
        119, "", "", ""
    ),
    NewOilData(
        120, "", "", ""
    ),
    NewOilData(
        121, "", "", ""
    ),
    NewOilData(
        122, "", "", ""
    ),
    NewOilData(
        123, "", "", ""
    ),
    NewOilData(
        124, "", "", ""
    ),
    NewOilData(
        125, "", "", ""
    ),
    NewOilData(
        126, "", "", ""
    ),
    NewOilData(
        127, "", "", ""
    ),
    NewOilData(
        128, "", "", ""
    ),
    NewOilData(
        129, "", "", ""
    ),
    NewOilData(
        130, "", "", ""
    ),
    NewOilData(
        131, "", "", ""
    ),
    NewOilData(
        132, "", "", ""
    ),
    NewOilData(
        133, "", "", ""
    ),
    NewOilData(
        134, "", "", ""
    ),
    NewOilData(
        135, "", "", ""
    ),
    NewOilData(
        136, "", "", ""
    ),
    NewOilData(
        137, "", "", ""
    ),
    NewOilData(
        138, "", "", ""
    ),
    NewOilData(
        139, "", "", ""
    ),
    NewOilData(
        140, "", "", ""
    ),
    NewOilData(
        141, "", "", ""
    ),
    NewOilData(
        142, "", "", ""
    ),
    NewOilData(
        143, "", "", ""
    ),
    NewOilData(
        144, "", "", ""
    ),
    NewOilData(
        145, "", "", ""
    ),
    NewOilData(
        146, "", "", ""
    ),
    NewOilData(
        147, "", "", ""
    ),
    NewOilData(
        148, "", "", ""
    ),
    NewOilData(
        149, "", "", ""
    ),
    NewOilData(
        150, "", "", ""
    ),
    NewOilData(
        151, "", "", ""
    ),
    NewOilData(
        152, "", "", ""
    ),
    NewOilData(
        153, "", "", ""
    ),
    NewOilData(
        154, "", "", ""
    ),
    NewOilData(
        155, "", "", ""
    ),
    NewOilData(
        156, "", "", ""
    ),
    NewOilData(
        157, "", "", ""
    ),
    NewOilData(
        158, "", "", ""
    ),
    NewOilData(
        159, "", "", ""
    ),
    NewOilData(
        160, "", "", ""
    ),
    NewOilData(
        161, "", "", ""
    ),
    NewOilData(
        162, "", "", ""
    ),
    NewOilData(
        163, "", "", ""
    ),
    NewOilData(
        164, "", "", ""
    ),
    NewOilData(
        165, "", "", ""
    ),
    NewOilData(
        166, "", "", ""
    ),
    NewOilData(
        167, "", "", ""
    ),
    NewOilData(
        168, "", "", ""
    ),
    NewOilData(
        169, "", "", ""
    ),
    NewOilData(
        170, "", "", ""
    ),
    NewOilData(
        171, "", "", ""
    ),
    NewOilData(
        172, "", "", ""
    ),
    NewOilData(
        173, "", "", ""
    ),
    NewOilData(
        174, "", "", ""
    ),
    NewOilData(
        175, "", "", ""
    ),
    NewOilData(
        176, "", "", ""
    ),
    NewOilData(
        177, "", "", ""
    ),
    NewOilData(
        178, "", "", ""
    ),
    NewOilData(
        179, "", "", ""
    ),
    NewOilData(
        180, "", "", ""
    ),
    NewOilData(
        181, "", "", ""
    ),
    NewOilData(
        182, "", "", ""
    ),
    NewOilData(
        183, "", "", ""
    ),
    NewOilData(
        184, "", "", ""
    ),
    NewOilData(
        185, "", "", ""
    ),
    NewOilData(
        186, "", "", ""
    ),
    NewOilData(
        187, "", "", ""
    ),
    NewOilData(
        188, "", "", ""
    ),
    NewOilData(
        189, "", "", ""
    ),
    NewOilData(
        190, "", "", ""
    ),
    NewOilData(
        191, "", "", ""
    ),
    NewOilData(
        192, "", "", ""
    ),
    NewOilData(
        193, "", "", ""
    ),
    NewOilData(
        194, "", "", ""
    ),
    NewOilData(
        195, "", "", ""
    ),
    NewOilData(
        196, "", "", ""
    ),
    NewOilData(
        197, "", "", ""
    ),
    NewOilData(
        198, "", "", ""
    ),
    NewOilData(
        199, "", "", ""
    ),
    NewOilData(
        200, "", "", ""
    ),
    NewOilData(
        201, "", "", ""
    ),
    NewOilData(
        202, "", "", ""
    ),
    NewOilData(
        203, "", "", ""
    ),
    NewOilData(
        204, "", "", ""
    ),
    NewOilData(
        205, "", "", ""
    ),
    NewOilData(
        206, "", "", ""
    ),
    NewOilData(
        207, "", "", ""
    ),
    NewOilData(
        208, "", "", ""
    ),
    NewOilData(
        209, "", "", ""
    ),
    NewOilData(
        210, "", "", ""
    ),
    NewOilData(
        211, "", "", ""
    ),
    NewOilData(
        212, "", "", ""
    ),
    NewOilData(
        213, "", "", ""
    ),
    NewOilData(
        214, "", "", ""
    ),
    NewOilData(
        215, "", "", ""
    ),
    NewOilData(
        216, "", "", ""
    ),
    NewOilData(
        217, "", "", ""
    ),
    NewOilData(
        218, "", "", ""
    ),
    NewOilData(
        219, "", "", ""
    ),
    NewOilData(
        220, "", "", ""
    ),
    NewOilData(
        221, "", "", ""
    ),
    NewOilData(
        222, "", "", ""
    ),
    NewOilData(
        223, "", "", ""
    ),
    NewOilData(
        224, "", "", ""
    ),
    NewOilData(
        225, "", "", ""
    ),
    NewOilData(
        226, "", "", ""
    ),
    NewOilData(
        227, "", "", ""
    ),
    NewOilData(
        228, "", "", ""
    ),
    NewOilData(
        229, "", "", ""
    ),
    NewOilData(
        230, "", "", ""
    ),
    NewOilData(
        231, "", "", ""
    ),
    NewOilData(
        232, "", "", ""
    ),
    NewOilData(
        233, "", "", ""
    ),
    NewOilData(
        234, "", "", ""
    ),
    NewOilData(
        235, "", "", ""
    ),
    NewOilData(
        236, "", "", ""
    ),
    NewOilData(
        237, "", "", ""
    ),
    NewOilData(
        238, "", "", ""
    ),
    NewOilData(
        239, "", "", ""
    ),
    NewOilData(
        240, "", "", ""
    ),
    NewOilData(
        241, "", "", ""
    ),
    NewOilData(
        242, "", "", ""
    ),
    NewOilData(
        243, "", "", ""
    ),
    NewOilData(
        244, "", "", ""
    ),
    NewOilData(
        245, "", "", ""
    ),
    NewOilData(
        246, "", "", ""
    ),
    NewOilData(
        247, "", "", ""
    ),
    NewOilData(
        248, "", "", ""
    ),
    NewOilData(
        249, "", "", ""
    ),
    NewOilData(
        250, "", "", ""
    ),
    NewOilData(
        251, "", "", ""
    ),
    NewOilData(
        252, "", "", ""
    ),
    NewOilData(
        253, "", "", ""
    ),
    NewOilData(
        254, "", "", ""
    ),
    NewOilData(
        255, "", "", ""
    ),
    NewOilData(
        256, "", "", ""
    ),
    NewOilData(
        257, "", "", ""
    ),
    NewOilData(
        258, "", "", ""
    ),
    NewOilData(
        259, "", "", ""
    ),
    NewOilData(
        260, "", "", ""
    ),
    NewOilData(
        261, "", "", ""
    ),
    NewOilData(
        262, "", "", ""
    ),
    NewOilData(
        263, "", "", ""
    ),
    NewOilData(
        264, "", "", ""
    ),
    NewOilData(
        265, "", "", ""
    ),
    NewOilData(
        266, "", "", ""
    ),
    NewOilData(
        267, "", "", ""
    ),
    NewOilData(
        268, "", "", ""
    ),
    NewOilData(
        269, "", "", ""
    ),
    NewOilData(
        270, "", "", ""
    ),
    NewOilData(
        271, "", "", ""
    ),
    NewOilData(
        272, "", "", ""
    ),
    NewOilData(
        273, "", "", ""
    ),
    NewOilData(
        274, "", "", ""
    ),
    NewOilData(
        275, "", "", ""
    ),
    NewOilData(
        276, "", "", ""
    ),
    NewOilData(
        277, "", "", ""
    ),
    NewOilData(
        278, "", "", ""
    ),
    NewOilData(
        279, "", "", ""
    ),
    NewOilData(
        280, "", "", ""
    ),
    NewOilData(
        281, "", "", ""
    ),
    NewOilData(
        282, "", "", ""
    ),
    NewOilData(
        283, "", "", ""
    ),
    NewOilData(
        284, "", "", ""
    ),
    NewOilData(
        285, "", "", ""
    ),
    NewOilData(
        286, "", "", ""
    ),
    NewOilData(
        287, "", "", ""
    ),
    NewOilData(
        288, "", "", ""
    ),
    NewOilData(
        289, "", "", ""
    ),
    NewOilData(
        290, "", "", ""
    ),
    NewOilData(
        291, "", "", ""
    ),
    NewOilData(
        292, "", "", ""
    ),
    NewOilData(
        293, "", "", ""
    ),
    NewOilData(
        294, "", "", ""
    ),
    NewOilData(
        295, "", "", ""
    ),
    NewOilData(
        296, "", "", ""
    ),
    NewOilData(
        297, "", "", ""
    ),
    NewOilData(
        298, "", "", ""
    ),
    NewOilData(
        299, "", "", ""
    ),
    NewOilData(
        300, "", "", ""
    ),
    NewOilData(
        301, "", "", ""
    ),
    NewOilData(
        302, "", "", ""
    ),
    NewOilData(
        303, "", "", ""
    ),
    NewOilData(
        304, "", "", ""
    ),
    NewOilData(
        305, "", "", ""
    ),
    NewOilData(
        306, "", "", ""
    ),
    NewOilData(
        307, "", "", ""
    ),
    NewOilData(
        308, "", "", ""
    ),
    NewOilData(
        309, "", "", ""
    ),
    NewOilData(
        310, "", "", ""
    ),
    NewOilData(
        311, "", "", ""
    ),
    NewOilData(
        312, "", "", ""
    ),
    NewOilData(
        313, "", "", ""
    ),
    NewOilData(
        314, "", "", ""
    ),
    NewOilData(
        315, "", "", ""
    ),
    NewOilData(
        316, "", "", ""
    ),
    NewOilData(
        317, "", "", ""
    ),
    NewOilData(
        318, "", "", ""
    )
)

val productsList = listOf<ProductData>(
    ProductData(
        id = 0,
        name = "Passenger Car",
        image = R.drawable.ic_car,
        productType = ProductTypes.PASSENGER_CAR
    ),
    ProductData(
        id = 1,
        name = "Commercial Vehicle",
        image = R.drawable.ic_commercial_cars,
        productType = ProductTypes.COMMERCIAL
    ),
    ProductData(
        id = 2,
        name = "Automatic Transmission",
        image = R.drawable.ic_motorcycle,
        productType = ProductTypes.AUTOMATIC_TRANSMISSION
    ),
    ProductData(
        id = 3,
        name = "Radiator Coolant",
        image = R.drawable.ic_radiator_coolant,
        productType = ProductTypes.RADIATOR_COOLANT
    ),
    ProductData(
        id = 4,
        name = "Heavy Duty Diesel",
        image = R.drawable.ic_motorcycle,
        productType = ProductTypes.HEAVY_DUTY_DIESEL
    ),
    ProductData(
        id = 5,
        name = "Hydraulic Brake Fluid",
        image = R.drawable.ic_radiator_coolant,
        productType = ProductTypes.HYDRAULIC_BRAKE_FLUID
    )
)

val passengerCarListEn = listOf<NewOilData>(
    NewOilData(
        0,
        "Gulf Formula Elite",
        "Premium Catalyst Compatible Passenger Car Motor Oil",
        "gulf_formula_elite_en.pdf"
    )
)
