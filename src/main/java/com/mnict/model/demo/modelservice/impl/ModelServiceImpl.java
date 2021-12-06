package com.mnict.model.demo.modelservice.impl;

import com.mnict.model.demo.modelservice.ModelService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.datavec.image.loader.NativeImageLoader;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.ImagePreProcessingScaler;
import org.nd4j.linalg.factory.Nd4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;

@Service
@Slf4j
public class ModelServiceImpl implements ModelService {
    @Override
    @SneakyThrows
    public void detect(MultipartFile imageFile) {
        int height = 28;
        int width = 28;
        int channels = 1;
        //target the model
        File locationToRetrive =  new File("MyMultiLayerNetwork.zip");      //Where to save the network. Note: the file is in .zip format - can be opened externally

        //Load the model
        MultiLayerNetwork model = MultiLayerNetwork.load(locationToRetrive, false);

        NativeImageLoader loader = new NativeImageLoader(height, width, channels);

        INDArray image = loader.asMatrix(imageFile.getInputStream());

        log.info(Arrays.toString(image.shape()));

        DataNormalization scaler = new ImagePreProcessingScaler(0,1);
        scaler.transform(image);

        INDArray output = model.output(image.reshape(new int[]{1, 784}));
        log.info("Evaluate model....");

        log.info(output.toString());
        log.info(Arrays.asList(0,1,2,3,4,5,6,7,8,9).toString());
    }
}
