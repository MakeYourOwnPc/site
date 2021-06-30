USE
    MakeYourOwnPc;
INSERT into gpus(name, consumption, price, stock, imagepath)
values ("Zotac GeForce RTX 3060 12 GB GAMING Twin Edge OC Video Card", 170, 850, 1, ""),
       ("MSI GeForce RTX 3080 10 GB GAMING X TRIO Video Card", 370, 1413.99, 2, "")
;

INSERT INTO memories(name, mtype, socket, consumption, price, amountmemories, stock, imagepath)
values ("G.Skill Ripjaws V 16 GB (2 x 8 GB) DDR4-3600 CL16 Memory", false, "DDR4", 30, 92.00, 2, 10, ""),
       ("Team AX2 1 TB 2.5 Solid State Drive", true, "sata", 5, 80.00, 1, 30, ""),
       ("Team MP33 1 TB M.2-2280 NVME Solid State Drive", true, "nvme", 5, 88.98, 1, 20, "");

INSERT INTO motherboards(name, amountslotnvme, amountslotsata, amountslotram, cpusocket, ramsocket, formfactor,
                         consumption, price, stock, imagepath)
values ("ASRock B550M Pro4 Micro ATX AM4 Motherboard", 3, 6, 4, "AM4", "DDR4", "micro-ATX", 50, 89.99, 30, ""),
       ("Gigabyte B550 GAMING X V2 ATX AM4 Motherboard",2,4,4,"AM4","DDR4","ATX",30,139.99,10,"");

INSERT INTO cpus(name, socket, integratedgpu, consumption, price, stock, imagepath)
VALUES ("AMD Ryzen 5 3600 3.6 GHz 6-Core Processor", "AM4", false, 65, 200, 20,
        ""),
       ("AMD Ryzen 5 5600X 3.7 GHz 6-Core Processor", "AM4", false, 65, 300, 12, "");

INSERT INTO psus(name, power, price, stock, imagepath)
values ("EVGA B5 550 W 80+ Bronze Certified Fully Modular ATX Power Supply", 550, 59, 30, ""),
       ("EVGA GQ 750 W 80+ Gold Certified Semi-modular ATX Power Supply", 750, 83.99, 56, "");

INSERT INTO pccases(name, formfactor, price, stock, imagepath)
values ("Phanteks Eclipse P300A Mesh ATX Mid Tower Case", "ATX", 49.99, 22, ""),
       ("Corsair iCUE 4000X RGB ATX Mid Tower Case", "ATX", 115.98, 60, "");

INSERT INTO countries(id, label)
values ("it", "italy"),
       ("es", "spain"),
       ("at", "austria"),
       ("de", "germany"),
       ("gr", "greece");




